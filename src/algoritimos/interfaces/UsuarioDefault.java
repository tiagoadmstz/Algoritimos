/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.interfaces;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author tiago.teixeira
 */
@Entity
@Table(name = "CAD_USUARIOS")
@Access(AccessType.PROPERTY)
@NamedQueries({
    @NamedQuery(name = "usuario.findAll", query = "SELECT user FROM UsuarioDefault AS user ORDER BY user.nomeUsuario"),
    @NamedQuery(name = "usuario.findById", query = "SELECT user FROM UsuarioDefault AS user WHERE user.id = :paramId"),
    @NamedQuery(name = "usuario.findByName", query = "SELECT user FROM UsuarioDefault AS user WHERE user.nomeCompleto = :paramNomeCompleto"),
    @NamedQuery(name = "usuario.validator", query = "SELECT user FROM UsuarioDefault AS user WHERE user.loginUsuario = :paramLogin AND user.password = :paramPassword")
})
public abstract class UsuarioDefault implements Serializable {

    protected Number id;
    protected String nomeCompleto;
    protected String loginUsuario;
    protected String password;
    protected boolean bloqueado;

    public UsuarioDefault() {
    }

    public UsuarioDefault(Number id, String nomeCompleto, String loginUsuario, String password, boolean bloqueado) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.loginUsuario = loginUsuario;
        this.password = password;
        this.bloqueado = bloqueado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    @Column(name = "nomeCompleto", nullable = false, unique = true, length = 255)
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    @Column(name = "loginUsuario", nullable = false, unique = true, length = 20)
    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    @Column(name = "password", nullable = false, length = 150)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "bloqueado", columnDefinition = "boolean")
    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    protected void clonar(UsuarioDefault usuario) {
        id = usuario.getId();
        nomeCompleto = usuario.getNomeCompleto();
        loginUsuario = usuario.getLoginUsuario();
        password = usuario.getPassword();
        bloqueado = usuario.isBloqueado();
    }

}

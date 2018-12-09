package br.com.crescer.holeinthewallapi.security;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
public class UserPrincipal implements UserDetails {

    private Long id;

    private String nome;

    private String email;

    private String apelido;

    @JsonIgnore
    private String senha;

    private Boolean ativo;

    private Boolean locked;

    private Boolean expired;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String nome, String email, String apelido, String senha, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.apelido = apelido;
        this.senha = senha;
        this.authorities = authorities;
        this.ativo = true;
        this.locked = true;
        this.expired = true;
    }

    public static UserPrincipal create(Usuario usuario) {
        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("implementar depois")
        );

        return new UserPrincipal(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getApelido(),
                usuario.getSenha(),
                authorities
        );
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }

}

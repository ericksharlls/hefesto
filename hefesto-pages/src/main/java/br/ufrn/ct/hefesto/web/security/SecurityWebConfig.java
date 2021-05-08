package br.ufrn.ct.hefesto.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService cronosUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasAnyRole("ADMINISTRADOR", "GESTOR_SETOR")
				
				.antMatchers("/chamado/atendimento/**").hasAnyRole("TECNICO_ATENDIMENTO", "GESTOR_SETOR")
				.antMatchers("/chamado/comum/**").hasAnyRole("ADMINISTRADOR", "TECNICO_ATENDIMENTO", "GESTOR_SETOR")
				.antMatchers("/usuario/gerenciamento/**").hasAnyRole("ADMINISTRADOR", "GESTOR_SETOR")
				.antMatchers("/usuario/comum/**").hasAnyRole("ADMINISTRADOR", "TECNICO_ATENDIMENTO", "GESTOR_SETOR")
				
				.antMatchers("/javax.faces.resource/**").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/estilo/**").permitAll()
				.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/login").permitAll().failureUrl("/login?error=true")
		.and()
			.logout()
				.logoutSuccessUrl("/login").permitAll()
		.and()
			.exceptionHandling().accessDeniedPage("/acessoNegado")
		.and()
		    .csrf().disable();
		// depois configurar o csrf spring security
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(cronosUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}

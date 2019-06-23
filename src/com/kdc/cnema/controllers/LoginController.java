package com.kdc.cnema.controllers;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdc.cnema.domain.Country;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.dtos.GetTokenDTO;
import com.kdc.cnema.dtos.LoginForm;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.service.CountryService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> main(@RequestBody LoginForm userSubmitted) {
		String message = "Default message";
		HttpStatus code = HttpStatus.FORBIDDEN;
		
		try {
			User user = userService.findOneByUsername(userSubmitted.getUsername()); 
			
			if(user != null) {
				
				if(user.getLogged()) {
					message = "Este usuario ya se encuentra activo";
					code = HttpStatus.FORBIDDEN;
				}else {
					if(passwordEncoder.matches(userSubmitted.getPassword(), user.getPassword())) {
						userService.updateLoggingState(user.getId(), true);
						message = JwtPayload.generateToken(new JwtPayload(user.getUsername(), new Date(), user.getType()+"", user.getId()+""));
						code = HttpStatus.OK;
					}else {
						message = "Credenciales incorrectas";
						code = HttpStatus.FORBIDDEN;
					}
				}
				
			}else {
				message = "Usuario no existe";
				code = HttpStatus.NOT_FOUND;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			message = "Error interno del servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<>(new ResponseDTO(message), code);
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> signup(@RequestBody @Valid User tempUser,   BindingResult result){
		
		String message = "Default Message";
		HttpStatus responseCode = HttpStatus.OK;
		
		if(result.hasErrors()) {
			message = "Revisar campos de usuario";
			responseCode = HttpStatus.BAD_REQUEST;
		}else {
			if(userService.findOneByUsername(tempUser.getUsername()) != null) {
				message = "Este usuario ya existe";
				responseCode = HttpStatus.CONFLICT;
			}else {
				try {
					Country country = countryService.findOneById(tempUser.getCountry().getId());
					//System.out.println("Val :"+country.getName()+ " Id"+ country.getId());
					
					tempUser.setCountry(country);
					tempUser.setCurrCredit(new BigDecimal(20));
					tempUser.setType(0);
					tempUser.setPassword(passwordEncoder.encode(tempUser.getPassword()));
					tempUser.setLogged(true);
					
					User user = userService.save(tempUser);
					
					message = JwtPayload.generateToken(new JwtPayload(user.getUsername(), new Date(), user.getType()+"", user.getId()+""));
					responseCode = HttpStatus.OK;
				}catch (Exception e) {
					e.printStackTrace();
					message = "Error al ingresar usuario";
					responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
				}
			}
			
		}
		
		return new ResponseEntity<>(new ResponseDTO(message), responseCode);
	}
	
	@RequestMapping(value= "/tokenDecode", method = RequestMethod.POST)
	public ResponseEntity<User> decodeJWT(@RequestBody GetTokenDTO tknAux){
		
		String token = tknAux.getToken();
		String password = tknAux.getPassword();
		
		User user = new User();
		HttpStatus code= HttpStatus.FORBIDDEN;
		
		try {
			
			JwtPayload payload = JwtPayload.decodeToken(token);
			
			user = userService.findOneById(Integer.parseInt(payload.getUid()));
			
			if(user != null) {
				if(passwordEncoder.matches(password,user.getPassword())) {
					code = HttpStatus.OK;
				}else {
					code=HttpStatus.FORBIDDEN;
					user = new User();
				}
				
			}else {
				user = new User();
				code = HttpStatus.NOT_FOUND;
			}
			
		}catch (io.jsonwebtoken.SignatureException e) {
			
			code = HttpStatus.FORBIDDEN;
		
		}catch (Exception e) {
			
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		
		}
		
		return new ResponseEntity<User>(user, code);
	}
	
	@RequestMapping(value = "/logout", method =  RequestMethod.POST)
	public ResponseEntity<ResponseDTO> logout(@RequestBody GetTokenDTO requestToken){
		String message = "Default Message";
		HttpStatus code = HttpStatus.OK;
		
		String token = requestToken.getToken();
		String password = requestToken.getPassword();
		
		try {
			
			JwtPayload payload = JwtPayload.decodeToken(token);
			
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			
			if(user != null) {
				if(passwordEncoder.matches(password,user.getPassword())) {
					if(user.getLogged()) {
						userService.updateLoggingState(user.getId(), false);
						message = "Sesión de usuario clausurada";
						code=HttpStatus.OK;
					}else {
						message = "Usuario actualmente no activo";
						code=HttpStatus.BAD_REQUEST;
					}
				}else {
					code=HttpStatus.FORBIDDEN;
					message = "Contraseña incorrecta"; 
				}
				
			}else {
				message = "Ususraio no encontrado";
				code = HttpStatus.NOT_FOUND;
			}
			
		}catch (io.jsonwebtoken.SignatureException e) {
			message = "Token incorrecto";
			code = HttpStatus.FORBIDDEN;
		
		}catch (Exception e) {
			
			e.printStackTrace();
			message = "Error interno de servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code); 
	}
	
	@RequestMapping(value = "/getTokenRip")
	public String test() {
		return JwtPayload.generateToken(new JwtPayload("Puto el que lo lea", new Date(), "sirviente", "Me la pela perro"));
	}
	
	@RequestMapping(value = "/test")
	public JwtPayload jwtTest(@RequestParam String token){
		return JwtPayload.decodeToken(token);
	}
	
	@RequestMapping(value = "/test2")
	public boolean jwtTest2(@RequestParam String token){
		return JwtPayload.isValidToken(token);
	}
	
}

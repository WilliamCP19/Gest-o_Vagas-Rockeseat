package com.william.gestao_vagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;



// @OpenAPIDefinition(node
// 	info = @Info(
// 		title = ,
// 		description = ,
// 		version = "1"
// 	)
// )
// @SecurityScheme(name = "jwt_auth", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SpringBootApplication
public class GestaoVagasApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestaoVagasApplication.class, args);
	}
}
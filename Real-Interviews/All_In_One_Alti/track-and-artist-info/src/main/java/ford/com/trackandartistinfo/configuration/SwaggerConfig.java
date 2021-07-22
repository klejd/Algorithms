package ford.com.trackandartistinfo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

//        return new Docket(DocumentationType.SWAGGER_2).select()
//
//                .apis(RequestHandlerSelectors
//
//                        .basePackage("net.guides.springboot2.springboot2swagger2.controller"))
//
//                .paths(PathSelectors.regex("/.*"))
//
//                .build().apiInfo(apiEndPointsInfo());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiEndPointsInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ford.com.trackandartistinfo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiEndPointsInfo() {

//        return new ApiInfoBuilder().title("Spring Boot REST API")
//
//                .description("Music Track Information REST API")
//
//                .contact(new Contact("Michael Yimam", "www.javaguides.net", "ramesh24fadatare@gmail.com"))
//
//                .license("Apache 2.0")
//
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//
//                .version("1.0.0")
//
//                .build();
        return new ApiInfo("REST Api Documentation",
                "REST Api Documentation",
                "1.0",
                "urn:tos",
                new Contact("Michael Yimam", "", "myimam@mum.edu"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());

    }
}

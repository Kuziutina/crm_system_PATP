package app.util;

import org.springframework.stereotype.Component;

@Component
public class LoginPasswordGenerator {


    public String generatePassword() {
        Generator generator = new Generator.GeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .usePunctuation(true)
                .build();
        return generator.generate(10);
    }

    public String generateLogin() {
        Generator generator = new Generator.GeneratorBuilder()
                .useDigits(true)
                .build();
        return generator.generate(10);
    }
}

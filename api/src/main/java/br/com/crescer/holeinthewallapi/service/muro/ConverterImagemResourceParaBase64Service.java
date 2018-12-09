package br.com.crescer.holeinthewallapi.service.muro;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class ConverterImagemResourceParaBase64Service {

    public String converterParaBase64(String caminhoDoGabarito) throws IOException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader
                .getResource(caminhoDoGabarito);

        String extensaoDoArquivo = "";
        String caminhoDoArquivoString = resource.getPath();

        int posicaoDoPonto = caminhoDoArquivoString.lastIndexOf('.');

        if (posicaoDoPonto > 0) {
            extensaoDoArquivo = caminhoDoArquivoString.substring(posicaoDoPonto + 1);
        }

        StringBuilder base64StringBuilder = new StringBuilder();
        base64StringBuilder.append("data:image/");
        base64StringBuilder.append(extensaoDoArquivo);
        base64StringBuilder.append(";base64,");

        Path caminhoDoArquivoPath = Paths.get(resource.toURI());

        base64StringBuilder.append(Base64.getEncoder().encodeToString(Files.readAllBytes(caminhoDoArquivoPath)));

        return base64StringBuilder.toString();
    }

}

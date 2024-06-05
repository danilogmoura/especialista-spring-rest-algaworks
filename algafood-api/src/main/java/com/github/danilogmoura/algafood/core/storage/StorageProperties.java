package com.github.danilogmoura.algafood.core.storage;

import com.amazonaws.regions.Regions;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {

    private Local local = new Local();

    private S3 s3 = new S3();

    private TipoStorage tipo = TipoStorage.LOCAL;

    public enum TipoStorage {
        LOCAL, S3
    }

    @Getter
    @Setter
    public static class Local {

        private Path diretorioFotos;

    }

    @Getter
    @Setter
    public static class S3 {

        private String endPoint;
        private String accessKeyId;
        private String secretAccessKey;
        private String bucket;
        private Regions region;
        private String photosPath;
    }

}

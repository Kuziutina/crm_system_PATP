package app.dn;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInfo {
    private String storageFileName;
    private String type;
    private String path;
}

package kr.hs.dgsw.SOPO_server_v2.global.common.aws.dto;

public record S3Res(
        String url
) {
    public static S3Res of(String url) {
        return new S3Res(
                url
        );
    }
}

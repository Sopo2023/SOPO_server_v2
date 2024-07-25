//package kr.hs.dgsw.SOPO_server_v2.domain.like.controller;
//
//import kr.hs.dgsw.SOPO_server_v2.domain.like.enums.LikeCategory;
//import kr.hs.dgsw.SOPO_server_v2.domain.like.service.LikeService;
//import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/like")
//@RequiredArgsConstructor
//public class LikeController {
//    private final LikeService likeService;
//
//    @PatchMapping
//    public Response patch(@RequestParam Long id, @RequestParam LikeCategory category) {
//        likeService.toggle(id, category);
//        return Response.of(HttpStatus.OK, "좋아요 생성/취소 완료");
//    }
//
//}

package com.yeoni.cock.controller.match;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.match.MatchRequest;
import com.yeoni.cock.domain.entity.Match;
import com.yeoni.cock.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
@Tag(name = "점수판 API", description = "점수판 관련 API")
public class MatchController {

    private final MatchService service;

    /** 목록 조회 */
    @Operation(summary = "점수판 목록", description = "점수판 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Match>>> list() {
        return ResponseEntity.ok(ApiResponse.success(service.listMatches()));
    }

    /** 단건 조회 */
    @Operation(summary = "점수판 상세", description = "점수판 상세")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Match>> get(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(service.getMatch(id)));
    }

    /** 등록 */
    @Operation(summary = "점수판 등록", description = "점수판 등록")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody MatchRequest req) {
        Match created = service.createMatch(req);
        //return ResponseEntity.status(HttpStatus.CREATED).body(created);
        return ResponseEntity.ok(ApiResponse.success("점수판 정보가 등록되었습니다.", null));
    }

    /** 수정 */
    @Operation(summary = "점수판 수정", description = "점수판 수정")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Integer id,
                                       @RequestBody MatchRequest req) {
        service.updateMatch(id, req);
        return ResponseEntity.ok(ApiResponse.success("점수판 정보가 수정되었습니다.", null));
    }

    /** 삭제(soft) */
    @Operation(summary = "점수판 삭제", description = "점수판 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        service.deleteMatch(id);
        return ResponseEntity.ok(ApiResponse.success("점수판 정보가 삭제되었습니다.", null));
    }
}

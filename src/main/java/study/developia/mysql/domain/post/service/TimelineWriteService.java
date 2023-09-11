package study.developia.mysql.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.post.entity.Timeline;
import study.developia.mysql.domain.post.repository.TimelineRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineWriteService {
    private final TimelineRepository timelineRepository;

    public void deliveryToTimeline(Long postId, List<Long> toMemberId) {
        List<Timeline> timelinse = toMemberId.stream()
                .map((memberId) -> Timeline.builder()
                        .memberId((memberId))
                        .postId(postId)
                        .build()
                ).toList();

        timelineRepository.bulkInsert(timelinse);
    }
}

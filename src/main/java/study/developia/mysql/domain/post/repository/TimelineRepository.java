package study.developia.mysql.domain.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import study.developia.mysql.domain.post.entity.Timeline;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TimelineRepository {
    public static final String TABLE = "Timeline";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final RowMapper<Timeline> ROW_MAPPER =
            (ResultSet resultSet, int rowNum) -> Timeline.builder()
                    .id(resultSet.getLong("id"))
                    .memberId(resultSet.getLong("memberId"))
                    .postId(resultSet.getLong("postId"))
                    .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
                    .build();

    public List<Timeline> findAllByMemberIdAndOrderByIdDesc(Long memberId, int size) {
        var sql = String.format("""
                SELECT * 
                FROM %s
                WHERE memberId = :memberId
                ORDER BY id DESC
                LIMIT :size    
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId)
                .addValue("size", size);

        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public List<Timeline> findAllByLessThanIdAndMemberIdAndOrderByIdDesc(Long id, Long memberId, int size) {
        var sql = String.format("""
                SELECT * 
                FROM %s
                WHERE memberId = :memberId and id < :id
                ORDER BY id DESC
                LIMIT :size    
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("memberId", memberId)
                .addValue("size", size);

        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public Timeline save(Timeline timeline) {
        if (timeline.getId() == null) {
            return insert(timeline);
        }
        throw new UnsupportedOperationException("Timeline은 갱신을 지원하지 않습니다");
    }

    private Timeline insert(Timeline timeline) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(timeline);
        long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Timeline.builder()
                .id(id)
                .memberId(timeline.getMemberId())
                .postId(timeline.getPostId())
                .createdAt(timeline.getCreatedAt())
                .build();
    }

    public void bulkInsert(List<Timeline> timelines) {
        var sql = String.format("""
                INSERT INTO `%s` (memberId, postId, createdAt) 
                VALUES (:memberId, :postId, :createdAt)
                """, TABLE);

        SqlParameterSource[] params = timelines
                .stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}

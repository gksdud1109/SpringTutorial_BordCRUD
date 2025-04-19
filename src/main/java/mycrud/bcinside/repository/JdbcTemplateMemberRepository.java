package mycrud.bcinside.repository;


import mycrud.bcinside.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member join(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", member.getUserId());
        parameters.put("password", member.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Member login(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?",
                memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        List<Member> result = jdbcTemplate.query("select * from member where userId = ?",
                memberRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM member";

        RowMapper<Member> memberRowMapper = (r, i) -> {
            Member rowObject = new Member();
            rowObject.setId((long)r.getInt("id"));
            rowObject.setUserId(r.getString("userId"));
            rowObject.setPassword(r.getString("password"));
            return rowObject;
        };

        return jdbcTemplate.query(sql, memberRowMapper);
    }
    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setUserId(rs.getString("userId"));
            member.setPassword(rs.getString("password"));
            return member;
        };
    }
}

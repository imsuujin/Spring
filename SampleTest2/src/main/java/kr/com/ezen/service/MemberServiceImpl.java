package kr.com.ezen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.com.ezen.dto.MemberVO;
import kr.com.ezen.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
     //생성자주입
	private final MemberMapper memberMapper;

//	@Autowired //필드주입
	//private MemberMapper mapper;
	@Override
	public void insertMember(MemberVO vo) {
		log.info("------->"+vo);
		memberMapper.insertMember(vo);
	}

	@Override
	public void updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.updateMember(vo);
	}

	public void deleteMember(int id) {
		memberMapper.deleteMember(id);

	}

	public MemberVO selectOne(int id) {
		// TODO Auto-generated method stub
		return memberMapper.selectOneMember(id);
	}

	public List<MemberVO> list() {
		// TODO Auto-generated method stub
		return memberMapper.selectAllmember();
	}

}

 package com.teulda.service.post.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.teulda.common.Photo;
import com.teulda.common.Search;
import com.teulda.service.domain.Comment;
import com.teulda.service.domain.Post;
import com.teulda.service.post.PostDao;

@Repository("postDaoImpl")
public class PostDaoImpl implements PostDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PostDaoImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public void addPost(Post post) throws Exception {
		sqlSession.insert("PostMapper.addPost", post);
		
	}

	@Override
	public Post getPost(int postNo) throws Exception {
		return sqlSession.selectOne("PostMapper.getPost",postNo);
	}

	@Override
	public void updatePost(Post post) throws Exception {
		
		sqlSession.update("PostMapper.updatePost", post);
		
	}

	@Override
	public void deletePost(int postNo) throws Exception {
		
		sqlSession.delete("PostMapper.deletePost", postNo);
		
	}

	@Override
	public List<Post> getPostList(Search search, char postCategory) throws Exception {
		
		Map<String, Object> map = new HashMap <String, Object>();
		
		map.put("search", search);
		map.put("postCategory", postCategory);
		
		return sqlSession.selectList("PostMapper.getPostList", map);
	}

	@Override
	public void addComment(Comment comment) throws Exception {
		
		sqlSession.insert("PostMapper.addComment", comment);
		
	}

	@Override
	public void updateComment(Comment comment) throws Exception {
		
		sqlSession.update("PostMapper.updateComment", comment);
	}

	@Override
	public void deleteComment(int commentNo) throws Exception {
		
		sqlSession.delete("PostMapper.deleteComment", commentNo);
		
	}

	@Override
	public List<Comment> getMycommentList(Search search, String nickname) throws Exception {
		
		Map<String, Object> map = new HashMap <String, Object>();
		
		map.put("search", search);
		map.put("nickname", nickname);
		
		return sqlSession.selectList("PostMapper.getMycommentList", map);
	}

	@Override
	public int getPostTotalCount(char postCategory) throws Exception {
		return sqlSession.selectOne("PostMapper.getPostTotalCount", postCategory);
	}

	@Override
	public int getMycommentTotalCount(String nickname) throws Exception {
		return sqlSession.selectOne("PostMapper.getCommentTotalCount", nickname);
	}

//	@Override
//	public void addPhoto(Photo photo) throws Exception {
//		sqlSession.insert("PhotoMapper.addPhoto", photo);
//		
//	}

	@Override
	public List<Comment> getCommentList(int postNo) throws Exception {
		return sqlSession.selectList("PostMapper.getCommentList", postNo);
	}

//	@Override
//	public List<Photo> getPhotoList(int postNo) throws Exception {
//		return sqlSession.selectList("PostMapper.getPhotoList", postNo);
//	}

	@Override
	public void updatePostViewCount(int postNo) throws Exception {
		sqlSession.update("PostMapper.updatePostViewCount", postNo);
		
	}

//	@Override
//	public void deletePhoto(int photoNo) throws Exception {
//		sqlSession.delete("PostMapper.deletePhoto", photoNo);
//		
//	}

	@Override
	public void deleteCommentUsePostNo(int postNo) throws Exception {
		sqlSession.delete("PostMapper.deleteCommentUsePostNo", postNo);
		
	}

//	@Override
//	public void deletePhotoUsePostNo(int postNo) throws Exception {
//		sqlSession.delete("PostMapper.deletePhotoUsePostNo", postNo);
//		
//	}

}

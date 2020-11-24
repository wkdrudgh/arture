package com.spring.proOne.mypage.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.proOne.mypage.vo.FavoriteVO;
import com.spring.proOne.member.vo.MemberVO;
import com.spring.proOne.gallery.vo.GalleryVO;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO{
   @Autowired
   private SqlSession sqlSession;
   

   @Override //내정보 불러오기
   public MemberVO selectMyInfo(String id) throws DataAccessException {
      MemberVO userinfo = sqlSession.selectOne("mapper.mypage.selectUserInfo",id);
      return userinfo;
   }

   @Override//내정보 수정
   public void updateMyInfo(Map myinfomap) throws DataAccessException {
      sqlSession.update("mapper.mypage.updateProfile",myinfomap);
   }
   
   @Override
   public void updateProFileImage(Map myinfomap) throws DataAccessException {
      sqlSession.update("mapper.mypage.updateProfileimage",myinfomap);
   }

   @Override//회원정보삭제
   public void deleteMyInfo(String id) throws DataAccessException {
      sqlSession.delete("mapper.mypage.deleteMember", id);
   }

   @Override//좋아요누른 게시글 확인하기
   public List<GalleryVO> myFavorite(String id) throws DataAccessException {
      List<GalleryVO> favoriteGallary = new ArrayList<GalleryVO>();
      List<FavoriteVO> myFavoriteList = sqlSession.selectList("mapper.mypage.selectfavorite", id);
      if(myFavoriteList.size()!=0) {
      for(FavoriteVO g : myFavoriteList) {
         GalleryVO v = sqlSession.selectOne("mapper.mypage.favoritegallery", g.getGalleryNO());
         favoriteGallary.add(v);
         }
      }
      return favoriteGallary;
   }

   @Override//내 게시글 불러오기
   public List<GalleryVO> myGallery(String id) throws DataAccessException {
      List<GalleryVO> myGalleryList = (List)sqlSession.selectList("mapper.mypage.selectmygallery", id);
      return myGalleryList;
   }

   @Override//비밀번호 확인
   public boolean confirmPassword(String id,String pwd) throws DataAccessException {
      boolean result = false;
      Map<String,String> userinfo = new HashMap<String, String>();
      userinfo.put("id", id);
      userinfo.put("pwd", pwd);
      int check = sqlSession.selectOne("mapper.mypage.confirmpassword",userinfo);
      if(check==1) {
         result = true;
      }
      return result;
   }

   @Override//아이디 삭제시 좋아요삭제
   public void deletefavorite(String id) throws DataAccessException {
      sqlSession.delete("mapper.mypage.deletefavorite",id);
   }

   @Override//좋아요 취소
   public void cancelfavorite(FavoriteVO favoriteVO) {
      sqlSession.delete("mapper.mypage.cancelfavorite",favoriteVO);
   }

   @Override//좋아요 추가 
   public void insertfavorite(FavoriteVO favoriteVO) throws DataAccessException {
      sqlSession.insert("mapper.mypage.insertfavorite", favoriteVO);
      
   }

   @Override//내 게시글 삭제
   public void deletemygallery(FavoriteVO favoriteVO) throws DataAccessException {
      sqlSession.delete("mapper.mypage.deletemygallery",favoriteVO);
      sqlSession.delete("mapper.mypage.deletefavorite",favoriteVO.getGalleryNO());
      sqlSession.delete("mapper.admin_member.deleteimagelist", favoriteVO.getGalleryNO());
         
      String path = "C:\\o_image\\galleryimage\\"+favoriteVO.getGalleryNO();
         File folder = new File(path);
         try {
             while(folder.exists()) {
            File[] folder_list = folder.listFiles(); //파일리스트 얻어오기
                  
            for (int j = 0; j < folder_list.length; j++) {
               folder_list[j].delete(); //파일 삭제 
               System.out.println("파일이 삭제되었습니다.");
                     
            }
                  
            if(folder_list.length == 0 && folder.isDirectory()){ 
               folder.delete(); //대상폴더 삭제
               System.out.println("폴더가 삭제되었습니다.");
            }
                  }
          } catch (Exception e) {
            e.getStackTrace();
         }
      
   }

   @Override//좋아요확인
   public int selectOverlappedFavorite(FavoriteVO favoriteVO) throws DataAccessException {
      return sqlSession.selectOne("mapper.mypage.selectOverlappedFavorite", favoriteVO);
      
   }

   @Override//받은좋아요와 작성개시글 수 가져오기
   public Map count(String id) throws DataAccessException {
      Map count = new HashMap();
      count.put("countgallery",sqlSession.selectOne("mapper.mypage.countgallery", id));
      count.put("countfavorite",sqlSession.selectOne("mapper.mypage.countfavorite", id));
      return count;
   }


   
}
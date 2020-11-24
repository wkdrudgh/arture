package com.spring.proOne.admin.member.dao;

import java.io.File;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.proOne.member.vo.MemberVO;

@Repository("adminMemberDAO")
public class AdminMemberDAOImpl implements AdminMemberDAO{
   @Autowired
   SqlSession sqlSession;
   
   @Override
   public List<MemberVO> selectAllMember() {
      List<MemberVO> membersList = sqlSession.selectList("mapper.admin_member.selectAllMembers");
      return membersList;
   }
   
   @Override
   public int modPwd(MemberVO temp) {
      int result = sqlSession.update("mapper.admin_member.updatePwd", temp);
      return result;
   }
   
   @Override
   public void deleteNotice(String id) throws DataAccessException {
      try {
         deletprofile(id);
      }catch(Exception e){
         e.printStackTrace();
      }
      
      List<Integer> applyNO = sqlSession.selectList("mapper.admin_member.apply",id);
      List<Integer> galleryNO = sqlSession.selectList("mapper.admin_member.gallery",id);
      for (int j = 0; j < applyNO.size(); j++) {
         int num = applyNO.get(j);
         sqlSession.delete("mapper.admin_member.deleteimagelist", num);
         deletfile(applyNO.get(j));
         System.out.println("딜리트에 들어가는 겔러리 넘버"+num);
      }
      for (int j = 0; j < galleryNO.size(); j++) {
         deletfile(galleryNO.get(j));
         int num = galleryNO.get(j);
         System.out.println("딜리트에 들어가는 겔러리 넘버"+num);
         sqlSession.delete("mapper.admin_member.deleteimagelist", num);
      }
      sqlSession.delete("mapper.admin_member.deletefavorite", id);
      sqlSession.delete("mapper.admin_member.deleteapply", id);
      sqlSession.delete("mapper.admin_member.deletegallery", id);
      sqlSession.delete("mapper.admin_member.deleteMember", id);

   }
   //삭제 메소드 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
   public void deletfile (int galleryNO) {
      String path = "C:\\o_image\\galleryimage\\"+galleryNO;
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
   public void deletprofile (String id) {
      String path = "C:\\o_image\\profileimage\\"+id;
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
   //삭제 메소드 끝!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

}
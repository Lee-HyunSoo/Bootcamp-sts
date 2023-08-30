package com.lhs.sts.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {
	
	/* 파일을 다운받아 저장할 경로 */
	private static String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";
	
	/* main page */
	@RequestMapping("/form")
	public String form() {
		return "uploadForm";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	/* 여러 파일을 저장하기 위한 MultipartHttpServletRequest */
	public String upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, Model model) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		/* 여러 파일을 저장하기 위한 map */
		Map<String, Object> map = new HashMap<String, Object>();
		
		/* 파일이 여러개가 들어올 시, 파일명이 여러개기 때문에 getParameterNames */
		Enumeration<?> enu = multipartRequest.getParameterNames();
		System.out.println(multipartRequest.getParameterNames().getClass());
		
		/* 가져온 파일명들을 하나씩 꺼내, map.put(file1, 이미지 파일명) */
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement(); // file1
			String value = multipartRequest.getParameter(name); // 이미지 파일명
			map.put(name, value);
		}
		
		/* fileProcess Method를 통해 첨부 된 파일명들 추출 */
		List<String> fileList = fileProcess(multipartRequest);
		map.put("fileList", fileList);
		model.addAttribute("map", map);
		return "result";
	}

	/*
	 * 1. 첨부 된 파일 명 추출한다.
	 * 2. 파일 이름에 대한 MultipartFile 객체를 가져온다.
	 * 3. MultipartFile을 통해 실제 파일 명을 가져온다.
	 * 4. 파일 이름을 하나씩 fileList에 저장한다.
	 * 5. 파일 추가를 눌렀는데, 파일을 선택하지 않았을 경우를 대비해 첨부 된 파일이 있는지 체크한다.
	 * 6. 해당 경로에 디렉토리가 없으면 그 경로에 해당하는 디렉터리를 만든 후 파일을 생성한다.
	 * 7. 임시로 저장 된 multipartFile을 실제 파일로 전송한다.
	 * 8. 첨부한 파일 이름들이 저장 된 fileList를 반환한다.
	 */
	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception {
		/* 파일명들을 저장할 List */
		List<String> fileList = new ArrayList<String>();
		/* 전달 된 파라미터 이름들(이미지 파일명들), 실제 이미지 파일이 아니다. */
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		
		/* 한번에 여러 파일을 업로드할 수도있기 때문에, while문을 통해 업로드 된 파일을 전부 찾는다. */
		while(fileNames.hasNext()) {
			String fileName = fileNames.next(); // file1
			
			/* multipartRequest에서  MultipartFile 객체 타입으로 파일 추출 */
			MultipartFile multipartFile = multipartRequest.getFile(fileName); 
			
			/* MultipartFile 내에서 실제 파일명을 추출 */
			String originalFileName = multipartFile.getOriginalFilename(); // duke.png
			/* 추출한 파일명을 list에 추가 */
			fileList.add(originalFileName); 
			 
			File file = new File(CURR_IMAGE_REPO_PATH + "\\" + fileName);
			/* 추출한 파일의 size가 0이라면? 파일이 없다는 뜻 */
			if (multipartFile.getSize() != 0) {
				if (!file.exists()) { // 경로 상에 파일이 존재하지 않을 때
					if (file.getParentFile().mkdirs()) { // 경로에 해당하는 디렉토리들을 생성
						file.createNewFile(); // 디렉토리 생성 이후에 파일 생성
					}
				}
			}
			/* 파일 전송 */
			multipartFile.transferTo(new File(CURR_IMAGE_REPO_PATH + "\\" + originalFileName));
		}
		return fileList;
	}
	

}

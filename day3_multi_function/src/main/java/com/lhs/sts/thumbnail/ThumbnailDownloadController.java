package com.lhs.sts.thumbnail;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

//@Controller
public class ThumbnailDownloadController {

	/* upload 후 파일이 저장되는 경로 */
	private static String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";
	
	/* 갤러리 같이 자주 왔다갔다해서 썸네일을 저장하는 것이 더 빠른 경우 사용 */
//	@RequestMapping("/download")
	protected void download(@RequestParam String imageFileName, HttpServletResponse response) throws Exception {
		/* 웹 브라우저 상에서 파일을 다룰 때 사용하는 Stream 객체 (Streaming) */
		OutputStream outputStream = response.getOutputStream();
		
		/* 파일이 저장 된 경로를 통해 File 객체 생성 */
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File image = new File(filePath);
		
		/* image1.png 처럼 들어온 파일명에서 확장자를 제거, thumbnail의 파일명으로 재사용 */
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0, lastIndex);
		
		/* 기존 파일을 thumbnail로 바꿔야 하기 때문에 새 File 객체 생성, 이 때 확장자 끝을 .png로 설정했지만 입력 된 파일이 png가 아닐 수 있다. */
		File thumbnail = new File(CURR_IMAGE_REPO_PATH + "\\thumbnail\\" + fileName + ".png");
		
		/* 원본 파일 존재한다면 */
		if (image.exists()) {
			/* thumbnail의 경로에 부모 폴더가 존재하지 않아 파일 생성 오류가 발생하는 것을 방지  */
			thumbnail.getParentFile().mkdirs();
			/* of(image) : thumbnail에 들어갈 원본 File 객체 */
			/* outputFormat("png")는 들어온 파일이 무조건 png가 아닐 수 있기 때문에, 강제로 png로 변환해준다. */
			Thumbnails.of(image).size(50, 50).outputFormat("png").toFile(thumbnail);
		}

		/* InputStream 객체 내에 파일 정보 저장 */
		FileInputStream inputStream = new FileInputStream(thumbnail);
		/* 파일을 buffer를 사용해 읽기위해, 8KB */
		byte[] buffer = new byte[1024 * 8]; // 
		
		/* 8KB씩 파일을 읽으며, outputStream에 저장 */
		while(true) {
			int count = inputStream.read(buffer); // 버퍼에 읽어들인 문자 개수
			if (count == -1) // 버퍼의 마지막에 도달했는지 체크
				break;
			outputStream.write(buffer, 0, count); // 어디에, startIndex, lastIndex
		}
		/* 사용한 stream 객체 종료 */
		inputStream.close();
		outputStream.close();	
	}
	
	/* 자주 접근하지 않고 보여주기만 하는 곳이라면, 썸네일을 실제로 저장하지 않는 것이 더 빠르다.  */
//	@RequestMapping("/download")
	protected void downloadNoFile(@RequestParam String imageFileName, HttpServletResponse response) throws Exception {
		/* 웹 브라우저 상에서 파일을 다룰 때 사용하는 Stream 객체 (Streaming) */
		OutputStream outputStream = response.getOutputStream();
		
		/* 파일이 저장 된 경로를 통해 File 객체 생성 */
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File image = new File(filePath);
		
		/* image1.png 처럼 들어온 파일명에서 확장자를 제거, thumbnail의 파일명으로 재사용 */
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0, lastIndex);
		
		/* 기존 파일을 thumbnail로 바꿔야 하기 때문에 새 File 객체 생성, 이 때 확장자 끝을 .png로 설정했지만 입력 된 파일이 png가 아닐 수 있다. */
		File thumbnail = new File(CURR_IMAGE_REPO_PATH + "\\thumbnail\\" + fileName + ".png");
		
		/* 원본 파일이 존재한다면 */
		if (image.exists()) {
			thumbnail.getParentFile().mkdirs();
			Thumbnails.of(image).size(50, 50).outputFormat("png").toOutputStream(outputStream);
		}
		else
			/* 없다면 메서드 종료 */
			return;
		
		/* 파일을 buffer를 사용해 읽기위해, 8KB */
		byte[] buffer = new byte[1024 * 8]; // 
		
		/* 사용한 stream 객체 종료 */
		outputStream.write(buffer);
		outputStream.close();	
	}

}

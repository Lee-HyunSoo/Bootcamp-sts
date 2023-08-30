package com.lhs.sts.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {

	/* upload 후 파일이 저장되는 경로 */
	private static String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";
	
	/* result.jsp(결과화면)에서 업로드 된 파일을 다시 다운로드하여 사용자에게 보여줄 때 호출하는 메서드 */
	@RequestMapping("/download")
	protected void download(@RequestParam String imageFileName, HttpServletResponse response) throws Exception {
		/* 웹 브라우저 상에서 파일을 다룰 때 사용하는 Stream 객체 (Streaming) */
		OutputStream outputStream = response.getOutputStream();
		
		/* 파일이 저장 된 경로를 통해 File 객체 생성 */
		String downFile = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File file = new File(downFile);
		
		/* POST 방식으로 데이터를 넘길 때는 Header를 타고가기 때문에, HttpResponseMessage의 Header 설정 */
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; filename=" + imageFileName);

		/* InputStream 객체 내에 파일 정보 저장 */
		FileInputStream inputStream = new FileInputStream(file);
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

}

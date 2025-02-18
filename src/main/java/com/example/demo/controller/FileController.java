package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FileDto;
import com.example.demo.model.FileReq;
import com.example.demo.service.FileService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/file")
@Tag(name="파일 API")
@Slf4j
public class FileController {

	/** 파일 Service */
	@Autowired
	FileService fileService;

	/**
	 * [파일] 다운로드
	 * @param request
	 * @param response
	 * @param fileDto
	 * @throws Exception
	 */
	@GetMapping("/download")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, FileDto fileDto) throws Exception {
		try {
			if(fileDto != null) {
				fileService.downloadFile(request ,response, fileDto);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@DeleteMapping("/delete/{delFileList}")
	public int deleteFileList(@PathVariable List<Integer> delFileList) {

		FileReq fileReq = new FileReq();
		fileReq.setDelFileList(delFileList);

		int result = 0;
		try {
			result = fileService.deleteFileList(fileReq);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}

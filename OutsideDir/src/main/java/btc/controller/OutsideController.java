package btc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OutsideController {

	@RequestMapping("/out/outsideFolder")
	public String outsideFolder() throws Exception {
		return "/out/outsideFolder";
	}
}

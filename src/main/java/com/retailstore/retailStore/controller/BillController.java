package com.retailstore.retailStore.controller;

import com.retailstore.retailStore.Config;
import com.retailstore.retailStore.exception.RequestInputValidationException;
import com.retailstore.retailStore.model.request.BillNetPayableRequestBody;
import com.retailstore.retailStore.model.response.BillNetPayableResponseBody;
import com.retailstore.retailStore.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/bill")
public class BillController {
    BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/payable")
    public ResponseEntity<BillNetPayableResponseBody> calculateNetPayable(
            @Valid @RequestBody BillNetPayableRequestBody body,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors())
            throw new RequestInputValidationException(bindingResult);
        String userId = (String) request.getAttribute(Config.USER_ID);
        return ResponseEntity.status(200).body(billService.calculateNetPayableAmount(body.getItems(), userId));
    }
}

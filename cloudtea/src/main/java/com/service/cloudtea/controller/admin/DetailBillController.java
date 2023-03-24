package com.service.cloudtea.controller.admin;

import com.service.cloudtea.model.DetailBill;
import com.service.cloudtea.service.impl.DetailBillSerViceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cloud-tea")
public class DetailBillController {
    @Autowired
    DetailBillSerViceImpl detailBillSerVice;
    @GetMapping("/detail-bill/{pageNumber}/{pageSize}")
    public Page<DetailBill> listAll(@PathVariable(name = "pageNumber") Integer pageNumber,
                                    @PathVariable(name = "pageSize") Integer pageSize){
        return detailBillSerVice.findAllDetailBill(PageRequest.of(pageNumber,pageSize));
    }
    @GetMapping("/get-detail-bill")
    public Page<DetailBill> listAllDetailBill(@RequestParam("idbill") Long id,
                                              @PathVariable(name = "pageNumber") Integer pageNumber,
                                              @PathVariable(name = "pageSize") Integer pageSize){
        return detailBillSerVice.findByBillId(id,PageRequest.of(pageNumber,pageSize));
    }

    @DeleteMapping("/detail-bill")
    public ResponseEntity dropDetailBill(@RequestParam("id") Long id){
        detailBillSerVice.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

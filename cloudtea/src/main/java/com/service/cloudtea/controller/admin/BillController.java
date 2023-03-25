package com.service.cloudtea.controller.admin;
import com.service.cloudtea.model.Bill;
import com.service.cloudtea.model.DetailBill;
import com.service.cloudtea.service.impl.BillServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cloud-tea")
@RequiredArgsConstructor
public class BillController {

    @Autowired
    BillServiceImpl billService;

    @PostMapping("/bill")
    public Bill addBill(@RequestParam("user_id") Long userid, @RequestParam("voucher_code") String voucherCode){
        Bill bill = new Bill();
        bill.setUserId(userid);
        DetailBill detailBill = new DetailBill();
        return billService.create(bill, detailBill, voucherCode);
    }

    @GetMapping("/bill/{pageNumber}/{pageSize}")
    public Page<Bill> listAll(@PathVariable(name = "pageNumber") Integer pageNumber,
                              @PathVariable(name = "pageSize") Integer pageSize){
        return billService.findAllBill(PageRequest.of(pageNumber,pageSize));
    }

    @GetMapping("/list-bill")
    public List<Bill> listAllBill(){
        return billService.listAll();
    }
}

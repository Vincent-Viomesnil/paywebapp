package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.Transfer;
import com.paymybuddy.paywebapp.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public Iterable<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public Optional<Transfer> getTransferById(Integer id) {
        return transferRepository.findById(id);
    }

    public Transfer addTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }
}

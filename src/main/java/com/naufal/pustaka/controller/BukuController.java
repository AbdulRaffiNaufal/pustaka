/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.naufal.pustaka.controller;

import com.naufal.pustaka.FormBuku;
import com.naufal.pustaka.model.Buku;
import com.naufal.pustaka.service.BukuService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IIISI
 */
public class BukuController {
    private final BukuService bukuService;
    private final FormBuku formBuku;
    
    public BukuController(FormBuku formBuku){
        this.formBuku = formBuku;
        bukuService = new BukuService();
    }
    
    public void bersihForm(){
        formBuku.getTxtBukuId().setText("");
        formBuku.getTxtKode().setText("");
        formBuku.getTxtJudul().setText("");
        formBuku.getTxtPengarang().setText("");
        formBuku.getTxtPenerbit().setText("");
        formBuku.getTxtTahunTerbit().setText("");
    }
    
    public void getBukuId(){
        Long id = Long.parseLong(formBuku.getTxtBukuId().getText());
        Buku buku = bukuService.getBuku(id);
        if (buku != null) {
            formBuku.getTxtKode().setText(buku.getKode());
            formBuku.getTxtJudul().setText(buku.getJudul());
            formBuku.getTxtPengarang().setText(buku.getPengarang());
            formBuku.getTxtPenerbit().setText(buku.getPenerbit());
            formBuku.getTxtTahunTerbit().setText(buku.getTahunTerbit());
        } else {
            JOptionPane.showMessageDialog(formBuku, "Data Tidak Ada");
        }
    }
    
    public void saveBuku(){
        Buku buku = new Buku();
        buku.setKode(formBuku.getTxtKode().getText());
        buku.setJudul(formBuku.getTxtJudul().getText());
        buku.setPengarang(formBuku.getTxtPengarang().getText());
        buku.setPenerbit(formBuku.getTxtPenerbit().getText());
        buku.setTahunTerbit(formBuku.getTxtTahunTerbit().getText());
        buku = bukuService.saveBuku(buku);
        if (buku !=null){
            formBuku.getTxtBukuId().setText(buku.getBukuId().toString());
            JOptionPane.showMessageDialog(formBuku, "Entri Data Berhasil");
        }else{
            JOptionPane.showMessageDialog(formBuku, "Entri Data Gagal");
        }
    }
    
    public void updateBuku(){
        Buku buku = new Buku();
        buku.setBukuId(Long.parseLong(formBuku.getTxtBukuId().getText()));
        buku.setKode(formBuku.getTxtKode().getText());
        buku.setJudul(formBuku.getTxtJudul().getText());
        buku.setPengarang(formBuku.getTxtPengarang().getText());
        buku.setPenerbit(formBuku.getTxtPenerbit().getText());
        buku.setTahunTerbit(formBuku.getTxtTahunTerbit().getText());
        buku = bukuService.updateBuku(buku);
        if (buku !=null){
            formBuku.getTxtBukuId().setText(buku.getBukuId().toString());
            JOptionPane.showMessageDialog(formBuku, "Update Data Berhasil");
        }else{
            JOptionPane.showMessageDialog(formBuku, "Update Data Gagal");
        }
    }
    
    public void deleteBuku(){
        Long id = Long.parseLong(formBuku.getTxtBukuId().getText());
        bukuService.deleteBuku(id);
        JOptionPane.showMessageDialog(formBuku, "Delete Data Berhasil");
    }
    
    public void viewTabel(){
        DefaultTableModel tabelModel = (DefaultTableModel) formBuku.getTabelBuku().getModel();
        tabelModel.setRowCount(0);
        List<Buku> bukuList = bukuService.getAllBuku();
        for(Buku buku : bukuList){
            Object[]row = {
                buku.getBukuId(),
                buku.getKode(),
                buku.getJudul(),
                buku.getPengarang(),
                buku.getPenerbit(),
                buku.getTahunTerbit()
            };
            tabelModel.addRow(row);
        }
    }
}

package bean;

import java.sql.Timestamp;

public class NhapHang {
   int idNhapHang;
   Timestamp ngaynhaphang;
   float tongtien;
public int getIdNhapHang() {
	return idNhapHang;
}
public void setIdNhapHang(int idNhapHang) {
	this.idNhapHang = idNhapHang;
}
public Timestamp getNgaynhaphang() {
	return ngaynhaphang;
}
public void setNgaynhaphang(Timestamp ngaynhaphang) {
	this.ngaynhaphang = ngaynhaphang;
}
public float getTongtien() {
	return tongtien;
}
public void setTongtien(float tongtien) {
	this.tongtien = tongtien;
}
public NhapHang(float tongtien) {
	super();
	this.tongtien = tongtien;
}

   public NhapHang(int idNhapHang, Timestamp ngaynhaphang, float tongtien) {
	super();
	this.idNhapHang = idNhapHang;
	this.ngaynhaphang = ngaynhaphang;
	this.tongtien = tongtien;
}
public NhapHang(){
	   
   }
}

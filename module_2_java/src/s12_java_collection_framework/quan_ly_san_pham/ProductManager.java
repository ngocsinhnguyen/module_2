package s12_java_collection_framework.quan_ly_san_pham;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> productList = new ArrayList<>();

    //thêm sản phẩm
    public void addProduct(Product product){
        productList.add(product);
    }

    //sửa thông tin theo ID
    public void editProduct(int id, String newName, double newPrice){
        for(Product product:productList){
            if (product.getId()==id){
                product.setName(newName);
                product.setPrice(newPrice);
                System.out.println("Đã cập nhật sản phẩm có id: "+ id);
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm có id: "+ id);
    }

    //xóa sản phẩm theo id
    public void removeProduct(int id) {
        Product toRemove = null;
        for (Product product : productList) {
            if (product.getId() == id) {
                toRemove = product;
                break;
            }
        }
        if (toRemove != null) {
            productList.remove(toRemove);
            System.out.println("Đã xóa sản phẩm có id = " + id);
        } else {
            System.out.println("Không tìm thấy sản phẩm để xóa.");
        }
    }




}

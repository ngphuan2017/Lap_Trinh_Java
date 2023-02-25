/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.annp.btth1;

import com.annp.Entity.Product;
import com.annp.Repository.Impl.ProductRepositoryImpl;
import com.annp.Repository.ProductRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author admin
 */
public class BTTH1 {

    public static void main(String[] args) {
        SessionFactory factory
                = HibernateUtils.getFactory();
        try (Session session = factory.openSession()) {
            Query q = session.createQuery("FROM Category");
            List category = q.list();
        }
        ProductRepository p = new ProductRepositoryImpl();
        Map<String, String> params = new HashMap<>();
        params.put("kw", "iphone");
        List<Product> products = p.getProducts(params);
        products.forEach(x -> System.out.printf("%d - %s - %.1f\n", x.getId(), x.getName(), x.getPrice()));
    }
}

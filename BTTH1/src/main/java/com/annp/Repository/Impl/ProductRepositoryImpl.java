/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.annp.Repository.Impl;

import com.annp.Entity.Product;
import com.annp.Repository.ProductRepository;
import com.annp.btth1.HibernateUtils;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author phuan
 */
public class ProductRepositoryImpl implements ProductRepository{
    @Override
    public List<Product> getProducts(Map<String, String> params){
        try(Session s = HibernateUtils.getFactory().openSession()){
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Object> q = b.createQuery();
            Root root = q.from(Product.class);
            q.select(root);
            
            String kw = params.get("kw");
            if(kw != null && kw.isEmpty()){
                Predicate p1 = (Predicate) b.like(root.get("name").as(String.class), String.format("%%%s%%", kw));
                q.where((Expression<Boolean>) p1);
            }
            
            Query query = s.createQuery(q);
            return query.getResultList();
        }
    } 
}

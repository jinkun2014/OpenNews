/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.jinkun.opennews.test;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * <p>
 * Run it as a Java application (not Android).
 *
 * @author Markus
 */
public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {
        //指定数据库版本和实体的包名
        Schema schema = new Schema(3, "com.ajin.wynews.domain");
        //指定Dao的包名
        schema.setDefaultJavaPackageDao("com.ajin.wynews.dao");
        // 保存模版代码
        schema.enableKeepSectionsByDefault();

        //增加一个表
//        addNote(schema);
//        addCustomerOrder(schema);
        addNewsChannel(schema);
        addNewsTopic(schema);
        addNewsRead(schema);
        addNewsDetail(schema);
        addNewsImage(schema);
        new DaoGenerator().generateAll(schema, "..\\opennews\\app\\src\\main\\java");
    }


//    "alt": "林子濠晒与林依轮合影",
//    "pixel": "435*327",
//    "ref": "<!--IMG#0-->",
//    "src": "http://img1.cache.netease.com/ent/2015/9/5/20150905093501853fd.jpg"
    private static void addNewsImage(Schema schema) {
        Entity newsImage = schema.addEntity("NewsImage");
        newsImage.implementsSerializable();
        newsImage.addIdProperty().autoincrement();
        newsImage.addStringProperty("alt").codeBeforeField("/*图片提示*/");
        newsImage.addStringProperty("pixel").codeBeforeField("/*图片像素*/");
        newsImage.addStringProperty("ref").codeBeforeField("/*图片应用*/");
        newsImage.addStringProperty("src").codeBeforeField("/*图片地址*/");
    }


    // private Integer isRead;
    private static void addNewsDetail(Schema schema) {
        Entity newsDetail = schema.addEntity("NewsDetail");
        newsDetail.implementsSerializable();
        newsDetail.addIdProperty().autoincrement();
        newsDetail.addStringProperty("title").codeBeforeField("/*标题*/").codeBeforeGetter("@JavascriptInterface");
        newsDetail.addStringProperty("body").codeBeforeField("/*内容*/").codeBeforeGetter("@JavascriptInterface");
        newsDetail.addBooleanProperty("hasNext").codeBeforeField("/*更多*/").codeBeforeGetter("@JavascriptInterface");
    }

    //    private String topicid;
    //    private String tname;
    //    private String tid;
    //
    //    private boolean isMe = true;
    //    private int orderNum;
    private static void addNewsChannel(Schema schema) {
        Entity newsChannel = schema.addEntity("NewsChannel");
        newsChannel.implementsSerializable();
        newsChannel.addIdProperty();
        newsChannel.addStringProperty("topicid").codeBeforeField("/*暂不知道有什么用*/");
        newsChannel.addStringProperty("tname").codeBeforeField("/*频道的名字*/");
        newsChannel.addStringProperty("tid").codeBeforeField("/*文章的ID*/");
        newsChannel.addIntProperty("isSelected").codeBeforeField("/*已选的频道0-->已选择的，1-->未选择的*/");
        newsChannel.addIntProperty("orderNum").codeBeforeField("/*排序*/");
    }

    //    private String title;
    //    private String imgsrc;
    //    private String digest;
    //    private String docid;
    private static void addNewsTopic(Schema schema) {
        Entity newsTopic = schema.addEntity("NewsTopic");
        newsTopic.implementsSerializable();
        newsTopic.addIdProperty().autoincrement();
        newsTopic.addStringProperty("title").codeBeforeField("/*标题*/");
        newsTopic.addStringProperty("imgsrc").codeBeforeField("/*缩略图*/");
        newsTopic.addStringProperty("digest").codeBeforeField("/*简述*/");
        newsTopic.addStringProperty("docid").codeBeforeField("/*详情ID*/");
    }

    // private String docid;
    // private Integer isRead;
    private static void addNewsRead(Schema schema) {
        Entity newsRead = schema.addEntity("NewsRead");
        newsRead.implementsSerializable();
        newsRead.addIdProperty().autoincrement();
        newsRead.addStringProperty("docid").codeBeforeField("/*详情ID*/");
        newsRead.addIntProperty("isRead").codeBeforeField("/*是否已读0未读，1已读*/");
    }

    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.implementsSerializable();
        note.addIdProperty();
        note.addStringProperty("text").notNull().codeBeforeField("//内容");
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }

}

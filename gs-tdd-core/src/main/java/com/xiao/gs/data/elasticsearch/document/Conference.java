//package com.xiao.gs.data.elasticsearch.document;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.core.geo.GeoPoint;
//
//import java.util.List;
//
//import static org.springframework.data.elasticsearch.annotations.FieldType.Date;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Document(indexName = "conference-index", type = "geo-class-point-type", shards = 1, replicas = 0,
//        refreshInterval = "-1")
//public class Conference {
//
//    @Id
//    private String id;
//
//    private String name;
//
//    @Field(type = Date)
//    private String date;
//
//    private GeoPoint location;
//
//    private List<String> keywords;
//
//}

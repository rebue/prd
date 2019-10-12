package rebue.prd.svc.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.so.PrdProductSpecSo;
import rebue.prd.svc.PrdProductSpecEsSvc;
import rebue.robotech.svc.impl.EsBaseSvcImpl;

@Service
public class PrdProductSpecEsSvcImpl extends EsBaseSvcImpl<PrdProductSpecSo> implements PrdProductSpecEsSvc {

    private static final Logger _log = LoggerFactory.getLogger(PrdProductSpecEsSvcImpl.class);

    @Resource
    private RestHighLevelClient esClient;

    @Override
    public String getIndexName() {
        return "prd-product-spec";
    }

    /**
     * 根据名称查询产品
     */
    @Override
    public List<PrdProductSpecMo> selectByName(String name) {
        _log.info("selectByName:{}", name);
        try {
            // 模糊查询
            QueryBuilder        queryBuilder        = QueryBuilders.matchPhraseQuery("name.pinyin", name);// 加上.keyword就是精确查找
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            // 开始位置
            searchSourceBuilder.from(0);
            // 每页数量
            searchSourceBuilder.size(12);
            searchSourceBuilder.query(queryBuilder);

            // 排序
            // Sort descending by _score (the default)
            searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
            // Also sort ascending by _id field
            searchSourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.ASC));

            // 查询指定字段 不查询指定字段
            // 该方法还接受一个由一个或多个通配符模式组成的数组，以控制以更细粒度的方式包含或排除哪些字段:
//           searchSourceBuilder.fetchSource(false);
//           String[] includeFields = new String[]{"name"};
//           String[] excludeFields = Strings.EMPTY_ARRAY;
//           searchSourceBuilder.fetchSource(includeFields, excludeFields);

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices(getIndexName());
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);

            _log.info("search返回值:{}", search);
            SearchHits             hits = search.getHits();
            List<PrdProductSpecMo> list = new ArrayList<PrdProductSpecMo>();
            for (SearchHit hit : hits) {
                Map<String, Object> tempSource = hit.getSourceAsMap();
                if (tempSource != null) {
                    PrdProductSpecMo mo = new PrdProductSpecMo();
                    mo.setName(tempSource.get("name").toString());
                    mo.setId(Long.parseLong(tempSource.get("id").toString()));
                    mo.setProductId(Long.parseLong(tempSource.get("productId").toString()));
                    mo.setUnit(tempSource.get("unit").toString());
                    mo.setMarketPrice(new BigDecimal(tempSource.get("marketPrice").toString()));
                    mo.setPicPath(tempSource.get("picPath").toString());
                    list.add(mo);
                }
            }

            _log.info("hits返回值:{}", list);
            return list;
        } catch (final IOException e) {
            final String msg = "ElasticSearch获取document失败";
            _log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

}

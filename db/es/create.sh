# 创建索引及配置ik和pinyin分词器
curl -XPUT '192.168.1.202:9200/prd-product-spec/?pretty' -H "Content-Type: application/json" -d '
{
    "settings" : {
       "analysis" : {
           "analyzer" : {
               "pinyin_analyzer" : {
                   "tokenizer" : "my_pinyin"
                   }
           },
           "tokenizer" : {
               "my_pinyin" : {
                   "type" : "pinyin",
                   "keep_first_letter":true,
                   "keep_separate_first_letter" : true,
                   "keep_full_pinyin" : true,
                   "keep_original" : false,
                   "limit_first_letter_length" : 16,
                   "lowercase" : true
               }
           }
       }
   }
}'

curl -XPOST '192.168.1.202:9200/prd-product-spec/_mappings?pretty' -H "Content-Type: application/json" -d '
{
  "properties": {
      "name": {
          "type": "keyword",
          "fields": {
              "pinyin": {
                  "type": "text",
                  "store": false,
                  "term_vector": "with_offsets",
                  "analyzer": "pinyin_analyzer",
                  "boost": 10
              }
          }
      }
  }
}'

# 测试
curl -XGET http://192.168.1.202:9200/prd-product-spec/_analyze?pretty -H "Content-Type: application/json" -d '
{
  "text": "百事可乐",
  "analyzer": "pinyin_analyzer"
}'


curl -XGET http://192.168.1.202:9200/prd-product-spec/_search -H 'Content-Type:application/json' -d '
{
  "query": {
    "match_phrase": {
      "name.pinyin": "kk"
    }
  }

}'
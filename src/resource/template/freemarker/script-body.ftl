<#setting number_format="#"> 
{
    "filtered": {
        "query": {
            "query_string": {
                "query": "*",
                "analyze_wildcard": true
            }
        },
        "filter": {
            "bool": {
			                     
                "must": [
                    <#if advertAddrs??>
                    {
                        "query": {
                            "match": {
                                "source_url.raw": {
                                    "query": "${advertAddrs}",
                                    "type": "phrase"
                                }
                            }
                        }
                    },
                    </#if>
                    {
                        "range": {
                        		"${dateRange}": {
                            	<#if startTime??>"gte": ${startTime},</#if>
                            	<#if endTime??>"lte": ${endTime},</#if>
                                "format": "epoch_millis"
                            }
                            
                        }
                    }
                ],
                	<#if advertAddr??>
			             "should" : [ ${advertAddr} ],
            		</#if>  
                
                "must_not": []
            }
        }
    }
}
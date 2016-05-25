<#setting number_format="#"> 
{
    "filtered": {
        "query": {
            "query_string": {
            	<#if advertAddr??>
            	"query": "${advertAddr}",
            	<#else>
                "query": "*",
                </#if>
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
                "must_not": []
            }
        }
    }
}
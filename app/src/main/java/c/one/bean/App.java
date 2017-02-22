package c.one.bean;

import java.util.List;

/**
 * Created by liujie on 2017/1/30.
 */

public class App{

    /**
     * error : false
     * results : [{"_id":"578c4ecf421aa90dea11ea17","createdAt":"2016-07-18T11:36:47.363Z","desc":"7.18","publishedAt":"2016-07-18T11:49:19.322Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f5xwnxj2vmj20dw0dwjsc.jpg","used":true,"who":"代码家"},{"_id":"57bdaa89421aa9125d96f4fe","createdAt":"2016-08-24T22:09:13.461Z","desc":"8-25","publishedAt":"2016-08-25T11:23:14.243Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f756vb8zl2j20u011haec.jpg","used":true,"who":"代码家"},{"_id":"56cc6d1d421aa95caa7076b4","createdAt":"2015-06-23T02:00:00.619Z","desc":"6.23","publishedAt":"2016-05-03T12:13:53.904Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bgw1etdsksgctqj20hs0qowgy.jpg","used":true,"who":"张涵宇"},{"_id":"57e9e46f421aa95ddb9cb547","createdAt":"2016-09-27T11:15:59.299Z","desc":"秋天来了","publishedAt":"2016-09-27T11:41:22.507Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f87z2n2taej20u011h11h.jpg","used":true,"who":"代码家"},{"_id":"56cc6d23421aa95caa707bf8","createdAt":"2015-07-28T01:32:22.272Z","desc":"7.28","publishedAt":"2015-07-28T04:12:24.908Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1eui8h92qyaj20p111idmw.jpg","used":true,"who":"张涵宇"},{"_id":"5760b299421aa940eb4e0f80","createdAt":"2016-06-15T09:42:49.747Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-22T11:57:27.848Z","source":"web","type":"福利","url":"http://ww3.sinaimg.cn/mw690/81309c56jw1f4v6mic7r5j20m80wan5n.jpg","used":true,"who":"龙龙童鞋"},{"_id":"579eb4b4421aa90d2fc94ba0","createdAt":"2016-08-01T10:32:20.10Z","desc":"8.1","publishedAt":"2016-08-01T12:00:57.45Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f6e1f1qmg3j20u00u0djp.jpg","used":true,"who":"代码家"},{"_id":"56cc6d1d421aa95caa7078db","createdAt":"2015-09-27T02:54:28.879Z","desc":"9.28\n","publishedAt":"2015-09-30T04:02:13.224Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bgw1ewgtnal1vzj20rw15ojww.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d22421aa95caa70792e","createdAt":"2015-10-14T12:13:41.39Z","desc":"10.15","publishedAt":"2015-10-15T11:01:18.105Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1ex0xcgp67kj20xc18g43b.jpg","used":true,"who":"张涵宇"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError(){
        return error;
    }

    public void setError(boolean error){
        this.error = error;
    }

    public List<ResultsBean> getResults(){
        return results;
    }

    public void setResults(List<ResultsBean> results){
        this.results = results;
    }

    public static class ResultsBean{
        /**
         * _id : 578c4ecf421aa90dea11ea17
         * createdAt : 2016-07-18T11:36:47.363Z
         * desc : 7.18
         * publishedAt : 2016-07-18T11:49:19.322Z
         * source : chrome
         * type : 福利
         * url : http://ww4.sinaimg.cn/large/610dc034jw1f5xwnxj2vmj20dw0dwjsc.jpg
         * used : true
         * who : 代码家
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id(){
            return _id;
        }

        public void set_id(String _id){
            this._id = _id;
        }

        public String getCreatedAt(){
            return createdAt;
        }

        public void setCreatedAt(String createdAt){
            this.createdAt = createdAt;
        }

        public String getDesc(){
            return desc;
        }

        public void setDesc(String desc){
            this.desc = desc;
        }

        public String getPublishedAt(){
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt){
            this.publishedAt = publishedAt;
        }

        public String getSource(){
            return source;
        }

        public void setSource(String source){
            this.source = source;
        }

        public String getType(){
            return type;
        }

        public void setType(String type){
            this.type = type;
        }

        public String getUrl(){
            return url;
        }

        public void setUrl(String url){
            this.url = url;
        }

        public boolean isUsed(){
            return used;
        }

        public void setUsed(boolean used){
            this.used = used;
        }

        public String getWho(){
            return who;
        }

        public void setWho(String who){
            this.who = who;
        }
    }
}

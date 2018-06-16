package personal.alg.maxent;
/** 
 * @author Chang Shu
 * @data 2015年12月10日
 */
public class FeatureDataStream  {
	private FeatureData next;
	private DataStream ds;
	public FeatureDataStream(DataStream ds) {
		this.ds = ds;
		if (this.ds.hasNext())
			next = Util.createFeatureData((String) this.ds.nextToken());
    
	}

	public FeatureData next() {
		while (next == null && this.ds.hasNext())
			next = Util.createFeatureData((String) this.ds.nextToken());
    
		FeatureData current = next;
		if (this.ds.hasNext()) {
			next = Util.createFeatureData((String) this.ds.nextToken());
		}
		else {
			next = null;
		}
		return current;
  }

	public boolean hasNext() {
		while (next == null && ds.hasNext())
			next = Util.createFeatureData((String) ds.nextToken());
		return next != null;
	}
  


}

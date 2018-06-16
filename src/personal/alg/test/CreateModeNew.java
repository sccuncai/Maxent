package personal.alg.test;


import personal.alg.maxent.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CreateModeNew {

	public CreateModeNew() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void train(String trainFileName,String modelFileNameNew)
	{
	    FileReader datafr = null;
		try {
			datafr = new FileReader(new File(trainFileName));
			FeatureDataStream es = new FeatureDataStream(new PlainTextByLineDataStreamS(datafr));
			//训练数据
			MaxentClassifier m = MaxentTrainer.train(es, "iis", 100);
			//保存模型
			boolean flag = ModeWriter.write(m, modelFileNameNew);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}
	
	public static double evalNew(MaxentClassifier classifier,String feature)
	{
		double prob = 0.0 ;
		FeatureData data = Util.createFeatureData(feature);

		ContinuumToDiscretization ctd = classifier.getContinuumToDiscretization();
		//如果是训练的模型是连续的数据,则预测数据划到分离散区间
		if (ctd != null){
			ctd.dataDiscretization(data,ctd.getDiscretizationMatrix(),ctd.getFeatureNameIndex());
		}

		DictionaryProbDist pDist = classifier.probClassify(data);
		/**预测为1的概率，类别为训练数据的各种类别
		 * 例如:训练数据每行中最后一个标识为"2",那么预测为"2"的概率就写prob = pDist.prob("2")*/
		prob = pDist.prob("1");
		return prob;
	}
	
	public static void predict(String testFileName,String modelFileNameNew)
	{
		MaxentClassifier classifier = null;
		DataStream ds = null;
		try {
			//读取模型
			classifier = ModeReader.read(modelFileNameNew);
			ds = new PlainTextByLineDataStreamS(new FileReader(new File(testFileName)));
			while (ds.hasNext()) {
			    String s = (String)ds.nextToken();
			    double p = evalNew(classifier,s);
			    //实际类别，用来算准确性
//			    String type = s.substring(s.lastIndexOf(' ') + 1);
			    //输出内容的概率
			    System.out.println(s+"---------"+p);
				break;
			}
			}
		catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	
	public static void main(String[] args) {
		String modelFileNameNew = "new_mode.txt";
		String trainFileName = "train_data.txt";
		String testFileName =  "test_data.txt";
		//训练数据
		train(trainFileName,modelFileNameNew);
		//预测结果
//		predict(testFileName,modelFileNameNew);
	}
}

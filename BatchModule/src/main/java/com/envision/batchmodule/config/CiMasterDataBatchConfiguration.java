package com.envision.batchmodule.config;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.envision.batchmodule.listener.CIMAsterDataNotificationListener;
import com.envision.batchmodule.service.CiMasterDataCSVReader;
import com.envision.batchmodule.service.CiMasterDataCsvItemProcessor;
import com.envision.cimodule.database.objects.CiMasterData;
import com.envision.common.exception.DataNotFoundException;
import org.springframework.core.io.Resource;
@Configuration
@EnableBatchProcessing
public class CiMasterDataBatchConfiguration {
	
   @Autowired
   public JobBuilderFactory jobBuilderFactory;

   @Autowired
   public StepBuilderFactory stepBuilderFactory;

   @Autowired
   public DataSource dataSource;

   @Autowired
   CiMasterDataCSVReader CiMasterDataReader;

   @Autowired
   EntityManagerFactory emf;
   
//   @Value("${CIMasterDataLocation}")
//   String ciMasterDataLocation;
   
   @Bean
   @JobScope
   public MultiResourceItemReader<CiMasterData> multiResourceItemReader( @Value("#{jobExecution['id']}") String jobId) {
	   
	   
     MultiResourceItemReader<CiMasterData> resourceItemReader = new MultiResourceItemReader<CiMasterData>();
     ClassLoader cl = this.getClass().getClassLoader();
     ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
     Resource[] resources = null;
     try {
         resources = resolver.getResources("file:H:/Works/Envision/LTAPP/BatchFilesFolder/"+jobId+"/*.csv");
     } catch (IOException e) {
         e.printStackTrace();
         
     }
     resourceItemReader.setResources(resources);
     resourceItemReader.setDelegate(CiMasterDataReader);
     return resourceItemReader;
   }
   
//   @Bean
//   public FlatFileItemReader<CiMasterData> reader() {
//	   
//      FlatFileItemReader<CiMasterData> reader = new FlatFileItemReader<CiMasterData>();
//      reader.setLineMapper(new DefaultLineMapper<CiMasterData>() {
//         {
//            setLineTokenizer(new DelimitedLineTokenizer() {
//               {
//                  setNames(new String[] { "state", "discom" });
//               }
//            });
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<CiMasterData>() {
//               {
//                  setTargetType(CiMasterData.class);
//               }
//            });
//         }
//      });
//      return reader;
//   }
   @Bean
   public CiMasterDataCsvItemProcessor processor() {
      return new CiMasterDataCsvItemProcessor();
   }
   

   @Bean
   public JpaItemWriter<CiMasterData> jpaWriter() {
       JpaItemWriter<CiMasterData> writer = new JpaItemWriter<CiMasterData>();
       writer.setEntityManagerFactory(emf);
       return writer;
   }
   
//   @Bean
//   public JdbcBatchItemWriter<CiMasterData> writer() {
//      JdbcBatchItemWriter<CiMasterData> writer = new JdbcBatchItemWriter<CiMasterData>();
//      writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CiMasterData>());
//      writer.setSql("INSERT INTO CiMasterDataS (first_name, last_name) VALUES (:firstName, :lastName)");
//      writer.setDataSource(dataSource);
//      return writer;
//   }
   @Bean
   public FlatFileItemWriter<CiMasterData> writer() {
	   FlatFileItemWriter<CiMasterData> writer = new FlatFileItemWriterBuilder<CiMasterData>().append(false).resource(new FileSystemResource("file1.csv"))
//			   .delimited().delimiter("|")
//			   .fieldExtractor(new FieldExtractor<CiMasterData>() {
//				
//				@Override
//				public Object[] extract(CiMasterData item) {
//					// TODO Auto-generated method stub
//					return item;
//				}
//			})
//			   .build();
			   .lineAggregator(new LineAggregator<CiMasterData>() {
				
				@Override
				public String aggregate(CiMasterData item) {
					// TODO Auto-generated method stub
					return item.getState()+"|"+item.getDiscom();
				}
			}).name("FlatFileItemWriter").
			   
			   build();
      return writer;
   }
   @Bean
   public Job importCiMasterDataJob(CIMAsterDataNotificationListener listener) {
      return jobBuilderFactory.get("importCiMasterDataJob").incrementer(
         new RunIdIncrementer()).listener(listener).flow(step1()).end().build();
   }
   @Bean
   public Step step1() {
//      return stepBuilderFactory.get("step1").<CiMasterData, CiMasterData>chunk(10).reader(multiResourceItemReader("")).processor(processor()).writer(writer()).
//    		  faultTolerant().skip(DataNotFoundException.class).skipLimit(1000000).allowStartIfComplete(true).
//    		  build();
	   

	      return stepBuilderFactory.get("step1").<CiMasterData, CiMasterData>chunk(10).reader(multiResourceItemReader("")).processor(processor()).writer(jpaWriter()).
	    		  faultTolerant().skip(DataNotFoundException.class).skipLimit(1000000).allowStartIfComplete(true).
	    		  build();
	   
   }

}
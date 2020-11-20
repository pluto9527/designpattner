package com.designpattern.design09.builder;

/**
 * 优化的构造器模式：不要director，而是通过返回Builder对象，调用方自己来构建构造链
 */
public class OptimizedBuilderPatternDemo {
	
	public static void main(String[] args) {
		Product product = new ConcreteBuilder()
				.field1("值1")
				.field2("值2")
				.field3("值3")
				.create();
		System.out.println(product);
		
		// 现在基本上流行的一些开源框架，构造器模式的运用，一般都是上面这种变种模式

		Builder builder = BuilderFactory.build();
		Product product2 = builder.field1("属性1").field2("属性2").create();
	}
	
	public static class Product {
		
		private String field1;
		private String field2;
		private String field3;
		
		public String getField1() {
			return field1;
		}
		public void setField1(String field1) {
			this.field1 = field1;
		}
		public String getField2() {
			return field2;
		}
		public void setField2(String field2) {
			this.field2 = field2;
		}
		public String getField3() {
			return field3;
		}
		public void setField3(String field3) {
			this.field3 = field3;
		}
		
		@Override
		public String toString() {
			return "Product [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
		}
		
	}
	
	public interface Builder {
		
		Builder field1(String value);
		Builder field2(String value);
		Builder field3(String value);
		Product create();
		
	}
	
	public static class ConcreteBuilder implements Builder {

		private Product product = new Product();

		private ConcreteBuilder() {

		}
		
		public Builder field1(String value) {
			System.out.println("在设置field1之前进行复杂的校验逻辑");  
			product.setField1(value); 
			return this;
		}
		
		public Builder field2(String value) {
			System.out.println("在设置field2之前进行复杂的数据格式转化逻辑");  
			product.setField2(value);
			return this;
		}

		public Builder field3(String value) {
			System.out.println("在设置field3之前进行复杂的数据处理逻辑，跟其他对象的数据进行关联");
			product.setField3(value);  
			return this;
		}

		public static Builder build() {
            return new ConcreteBuilder();
        }
		
		public Product create() {
			return product;
		}
		
	}

	public static class BuilderFactory {
		public static Builder build() {
			return new ConcreteBuilder();
		}
	}
	
}
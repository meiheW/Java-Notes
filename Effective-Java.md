<div align="center">
    <h1>
    	Effective-Java
	</h1>
</div>

## About	
	
	本文档是我在学习《Effective Java》这本书时做的笔记。这本书主要讨论了JAVA语言实际编码中常用的一些有益的做法，
	包含很多示例，涉及到设计模式和习惯用法。许多条目也包含了一个或者多个应该在实践过程中应该避免的程序示例。

## 创建和销毁对象

- 第1条：考虑用静态工厂方法代替构造器  

	静态工厂方法与构造器相比的**优势**：  

	* 静态工厂方法有名称。

	* 不必每次调用他们的时候都创建一个新对象。

	* 它们可以返回原返回类型的任何子类型的对象。
	
	* 在创建参数化类型实例的时候，它们使代码变得更加简洁。
	
	**缺点**在于：
	
	* 类如果不含有公有的或者受保护的构造器，就不能被子类化。
	
	* 实质与其他的静态方法没有任何区别。
	
- 第2条：遇到多个构造器参数时要考虑用构建器  
	
	静态工厂和构造器有个共同的局限性：他们都不能很好地扩展到大量的参数。  
	
	* 方法1：重叠构造器
	
	```java
	public class NutritionFacts {
		private final int servingSize;
		private final int servings;
		private final int calories;
		private final int fat;
		private final int sodium;
		private final int carbohydrate;

		public NutritionFacts(int servingSize, int servings){
			this(servingSize, servings, 0);
		}
		public NutritionFacts(int servingSize, int servings, int calories) {
			this(servingSize, servings, calories, 0);
		}
		public NutritionFacts(int servingSize, int servings, int calories, int fat) {
			this(servingSize, servings, calories, fat, 0);
		}
		public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
			this(servingSize, servings, calories, fat, sodium, 0);
		}
		
		public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
			this.servingSize = servingSize;
			this.servings = servings;
			this.calories = calories;
			this.fat = fat;
			this.sodium = sodium;
			this.carbohydrate = carbohydrate;
		}
	}
	```  

	* 方法2：JavaBeans模式 - 阻止了把类做成不可变的可能
	
	```java
	public class NutritionFactsJBModle {
		private int servingSize = -1;
		private int servings = -1;
		private int calories = 0;
		private int fat = 0;
		private int sodium = 0;
		private int carbohydrate = 0;
		
		public NutritionFactsJBModle(){}

		//setters
		public void setServingSize(int servingSize) {
			this.servingSize = servingSize;
		}

		public void setServings(int servings) {
			this.servings = servings;
		}

		public void setCalories(int calories) {
			this.calories = calories;
		}

		public void setFat(int fat) {
			this.fat = fat;
		}

		public void setSodium(int sodium) {
			this.sodium = sodium;
		}

		public void setCarbohydrate(int carbohydrate) {
			this.carbohydrate = carbohydrate;
		}
	}
	
	//client
	NutritionFactsJBModle cocaCola = new NutritionFactsJBModle();
	cocaCola.setServingSize(240);
	cocaCola.setServings(8);
	cocaCola.setCalories(100);
	cocaCola.setSodium(35);
	cocaCola.setCarbohydrate(27);
	```  

	* 方法3：Builder模式 - builder利用单独的方法来设置每个参数  
	不足之处在于：为了创建对象，必须先创建它的构建器,一般在参数量很大的情况下使用。
	
	```java
	public class NutritionFactsBuilderPattern {
		private int servingSize;
		private int servings;
		private int calories;
		private int fat;
		private int sodium;
		private int carbohydrate;
		
		public static class Builder{
			private int servingSize;
			private int servings;
			
			private int calories = 0;
			private int fat = 0;
			private int sodium = 0;
			private int carbohydrate = 0;
			
			public Builder(int servingSize, int servings){
				this.servingSize = servingSize;
				this.servings = servings;
			}
			public Builder calories(int val){
				calories = val;
				return this;
			}
			public Builder fat(int val){
				fat = val;
				return this;
			}
			public Builder sodium(int val){
				sodium = val;
				return this;
			}
			public Builder carbohydrate(int val){
				carbohydrate = val;
				return this;
			}
			
			public NutritionFactsBuilderPattern build(){
				return new NutritionFactsBuilderPattern(this);
			}
			
		}

		
		private NutritionFactsBuilderPattern(Builder builder){
			servingSize = builder.servingSize;
			servings = builder.servings;
			calories = builder.calories;
			fat = builder.fat;
			sodium = builder.sodium;
			carbohydrate = builder.carbohydrate;
		}

	}

	//client
	NutritionFactsBuilderPattern cocaCola = new NutritionFactsBuilderPattern.Builder(240,8).
	calories(100).sodium(35).carbohydrate(27).builder();
	```

- 第3条：用私有构造器或者枚举类型强化Singleton属性  





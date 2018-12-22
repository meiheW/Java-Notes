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

	静态工厂方法与构造器相比的优势：  

	* 静态工厂方法有名称。

	* 不必每次调用他们的时候都创建一个新对象。

	* 它们可以返回原返回类型的任何子类型的对象。
	
	* 在创建参数化类型实例的时候，它们使代码变得更加简洁。
	
	缺点在于：
	
	* 类如果不含有公有的或者受保护的构造器，就不能被子类化。
	
	* 实质与其他的静态方法没有任何区别。
	
- 第2条：遇到多个构造器参数时要考虑用构建器  
	
	静态工厂和构造器有个共同的局限性：他们都不能很好地扩展到大量的参数。  
	
	* 方法1：重叠构造器
```
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
	
	}```
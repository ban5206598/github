package MyTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestOne {

	private String username;
	private String password;
	private int[] age;

	public void setUserName(String username) {
		this.username = username;
	}

	private void setPassWord(String password) {
		this.password = password;
	}

	public static void test01() throws ClassNotFoundException {

		Class c1 = TestOne.class;
		Class c2 = Class.forName("MyTest.TestOne");

		String package01 = c1.getPackage().getName();
		String package02 = c2.getPackage().getName();
		System.out.println("package01=" + package01);
		System.out.println("package02=" + package02);

		// 获取修饰符
		int mod = c1.getModifiers();
		String modifer = Modifier.toString(mod);
		System.out.println("modifier = " + modifer);

		// 获取指定类的完全限定名
		String className = c1.getName();
		System.out.println("className =" + className);

		// 获取指定的父类
		Class superClazz = c1.getSuperclass();
		String superClazzName = superClazz.getName();
		System.out.println("superClazzName =" + superClazzName);

		// 获取实现的接口
		Class[] interfaces = c1.getInterfaces();
		for (Class t : interfaces) {
			System.out.println("interfacesName =" + t.getName());
		}

		// 获取指定类的成员变量
		Field[] fields = c1.getDeclaredFields();
		for (Field field : fields) {

			String modifier = Modifier.toString(field.getModifiers());
			// 字段的访问修饰符
			Class type = field.getType();
			String name = field.getName();

			if (type.isArray()) {

				String arrType = type.getComponentType().getName() + "[]";
				System.out.println("" + modifier + " " + arrType + "" + name
						+ ";");

			} else {
				System.out.println("" + modifier + " " + type + " " + name
						+ ";");
			}

		}

		// 获取类的构造方法
		Constructor[] constructors = c1.getDeclaredConstructors();
		for (Constructor constructor : constructors) {

			String name = constructor.getName();
			String modifier = Modifier.toString(constructor.getModifiers());
			System.out.println("" + modifier + " " + name + "(");

			Class[] paramTypes = constructor.getParameterTypes();// 获取构造方法中的参数
			for (int i = 0; i < paramTypes.length; i++) {
				if (i > 0) {
					System.out.print(",");
				}

				if (paramTypes[i].isArray()) {

					System.out.println(paramTypes[i].getComponentType()
							.getName() + "[]");

				} else {
					System.out.println(paramTypes[i].getName());
				}
			}
			System.out.println();
		}

		// 获取成员方法
		Method[] methods = c1.getDeclaredMethods();
		for (Method method : methods) {
			String modifier = Modifier.toString(method.getModifiers());
			Class returnType = method.getReturnType();
			if (returnType.isArray()) {
				String arrType = returnType.getComponentType().getName() + "[]";
				System.out.println("" + modifier + " " + arrType + " "
						+ method.getName() + "(");

			} else {

				System.out.print("" + modifier + " " + returnType.getName()
						+ " " + method.getName() + "(");
			}

			Class[] paramTypes = method.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {

				if (i > 0) {
					System.out.print(",");
				}
			}
		}

	}

	public static void test02() throws InstantiationException,
			IllegalAccessException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, InvocationTargetException {

		// 反射调用方法，可以通过Method类的invoke方法实现动态方法的调用
		// public Object invoke(Object obj, Object..args)
		// 第一个参数代表对象
		// 第二个参数代表执行方法上的参数
		// 若反射要调用类的某个私有方法，可以在这个私有方法对应的Method对象上先
		// 调用setAccessible(true)
		Class c1 = TestOne.class;
		TestOne tt = (TestOne) c1.newInstance();
		System.out.println("username ==" + tt.username);
		System.out.println("password==" + tt.password);
		Method method = c1.getDeclaredMethod("setUserName", String.class);
		method.invoke(tt, "java反射学习");

		System.out.println("username == " + tt.username);

		method = c1.getDeclaredMethod("setPassWord", String.class);
		method.setAccessible(true);
		method.invoke(tt, "反射执行某个Private修饰的方法");
		System.out.println("passwrod == " + tt.password);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Java反射的功能
		// 1.可以判断运行时对象所属的类
		// 2.可以判断运行时对象所具有的成员变量和方法
		// 3.通过反射甚至可以调用private的方法
		// 4.生成动态代理
		// 实现java反射的类
		// - Class： 它表示正在运行的Java应用程序中的类和接口
		// - Field： 提供有关类或接口的属性信息，以及对它的动态访问权限
		// - Constructor：提供关于类的单个构造方法的信息以及对它的访问权限
		// - Method：提供关于类或接口中摸个方法的信息

		// 注意：Class类是Java反射中最重要的一个功能类，所有获取对象的信息
		// (包括：方法/属性/构造方法/访问权限)都需要它来实现

		/*
		 * 编写Java反射程序的步骤 必须首先获取一个类的Class对象 例如 Class c1 = Test.class Class c2 =
		 * Class.forName("com.refection.Test"); Class c3 = new
		 * Test().getClass(); 2.然后分别调用Class对象中的方法来获取一个类的属性/方法/构造方法的结构
		 * 注意：如果要能够正常的获取勒种方法/属性/构造方法应该重点掌握如下反射类 Field Constructor Method
		 */

	}

}

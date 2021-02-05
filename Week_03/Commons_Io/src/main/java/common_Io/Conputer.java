package common_Io;

/**
 * @author Matthew
 * @date 2021/2/1 20:53
 */
public class Conputer
{
	String cpu;
	int cores;

	@Override public String toString()
	{
		return "cpu: " + cpu + "cores: " + cores;
	}

	public static final class ConputerBuilder
	{
		String cpu;
		int cores;

		private ConputerBuilder()
		{
		}

		public static ConputerBuilder aConputer()
		{
			return new ConputerBuilder();
		}

		public ConputerBuilder withCpu(String cpu)
		{
			this.cpu = cpu;
			return this;
		}

		public ConputerBuilder withCores(int cores)
		{
			this.cores = cores;
			return this;
		}

		public Conputer build()
		{
			Conputer conputer = new Conputer();
			conputer.cores = this.cores;
			conputer.cpu = this.cpu;
			return conputer;
		}
	}

	public static void main(String[] args)
	{
		Conputer amd = new ConputerBuilder().withCores(2).withCpu("AMD").build();
		System.out.println(amd.toString());
	}
}

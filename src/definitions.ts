export interface PhotolibPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}

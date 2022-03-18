export interface PhotolibPlugin {
  echo(options: { value: string }): Promise<any>;
  getAlbums(): Promise<ReaddirResult>;
}

export interface ReaddirResult {
  /**
   * List of files and directories inside the directory
   *
   * @since 1.0.0
   */
  files: string;
}

export interface ReaddirOptions {
  /**
   * The path of the directory to read
   *
   * @since 1.0.0
   */
  path: string;
}
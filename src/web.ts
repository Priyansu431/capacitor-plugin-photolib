import { WebPlugin } from '@capacitor/core';

import type { PhotolibPlugin } from './definitions';

export class PhotolibWeb extends WebPlugin implements PhotolibPlugin {
  async getAlbums(): Promise<ReaddirResult> {
    let ReaddirResult: ReaddirResult = {
      files: 'getAlbums'
    }
    return ReaddirResult;
  };

  async echo(options: { value: string }): Promise<{ value: string }> {
    return options
  };
}

export interface ReaddirResult {
  /**
   * List of files and directories inside the directory
   *
   * @since 1.0.0
   */
  files: string;
}

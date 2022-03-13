import { WebPlugin } from '@capacitor/core';

import type { PhotolibPlugin } from './definitions';

export class PhotolibWeb extends WebPlugin implements PhotolibPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

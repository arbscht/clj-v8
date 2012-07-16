#ifndef _V8WRAPPER_H
#define _V8WRAPPER_H

#ifdef __cplusplus
extern "C" {
#endif
  // compiles and executes javascript and returns the script return value as string
  wchar_t *run(wchar_t *js);

  // releases memory returned from the last run call
  int cleanup(void *lastresult);

#ifdef __cplusplus
}
#endif

#endif // _V8WRAPPER_H

import * as React from 'react';
import {
  Form,
  Select,
  InputNumber,
  Switch,
  Radio,
  Slider,
  Button,
  Upload,
  Icon,
  Rate
} from 'antd';
import { FormComponentProps } from 'antd/lib/form';

class Page1 extends React.Component<FormComponentProps> {
  public handleSubmit = (e: any) => {
    e.preventDefault();
    this.props.form.validateFields((err: any, values: any) => {
      if (!err) {
        // console.log('Received values of form: ', values);
      }
    });
  };
  public normFile = (e: any) => {
    // console.log('Upload event:', e);
    if (Array.isArray(e)) {
      return e;
    }
    return e && e.fileList;
  };

  public render() {
    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 }
    };
    return (
      <Form onSubmit={this.handleSubmit}>
        <Form.Item {...formItemLayout} label="纯文本">
          <span className="ant-form-text">中国</span>
        </Form.Item>
        <Form.Item {...formItemLayout} label="选择" hasFeedback={true}>
          {getFieldDecorator('select', {
            rules: [{ required: true, message: '请选择你的国家！' }]
          })(
            <Select placeholder="请选择一个国家">
              <Select.Option value="china">中国</Select.Option>
              <Select.Option value="usa">美国</Select.Option>
            </Select>
          )}
        </Form.Item>

        <Form.Item {...formItemLayout} label="选择[多选]">
          {getFieldDecorator('select-multiple', {
            rules: [
              {
                required: true,
                message: '请选择你喜欢的颜色！',
                type: 'array'
              }
            ]
          })(
            <Select mode="multiple" placeholder="请选择喜欢的颜色">
              <Select.Option value="red">红色</Select.Option>
              <Select.Option value="green">绿色</Select.Option>
              <Select.Option value="blue">蓝色</Select.Option>
            </Select>
          )}
        </Form.Item>

        <Form.Item {...formItemLayout} label="输入数字">
          {getFieldDecorator('input-number', {
            initialValue: 3,
            rules: [
              {
                required: true,
                message: '请输入数字'
              }
            ]
          })(<InputNumber min={1} max={10} />)}
          <span className="ant-form-text"> 次</span>
        </Form.Item>

        <Form.Item {...formItemLayout} label="开关">
          {getFieldDecorator('switch', { valuePropName: 'checked' })(
            <Switch />
          )}
        </Form.Item>

        <Form.Item {...formItemLayout} label="滑块">
          {getFieldDecorator('slider')(
            <Slider
              marks={{ 0: 'A', 20: 'B', 40: 'C', 60: 'D', 80: 'E', 100: 'F' }}
            />
          )}
        </Form.Item>

        <Form.Item {...formItemLayout} label="单选按钮">
          {getFieldDecorator('radio-group', {
            rules: [
              {
                required: true,
                message: '该选项不能为空'
              }
            ]
          })(
            <Radio.Group>
              <Radio value="a">选项 1</Radio>
              <Radio value="b">选项 2</Radio>
              <Radio value="c">选项 3</Radio>
            </Radio.Group>
          )}
        </Form.Item>

        <Form.Item {...formItemLayout} label="单选按钮">
          {getFieldDecorator('radio-button', {
            rules: [
              {
                required: true,
                message: '该选项不能为空'
              }
            ]
          })(
            <Radio.Group>
              <Radio.Button value="a">选项 1</Radio.Button>
              <Radio.Button value="b">选项 2</Radio.Button>
              <Radio.Button value="c">选项 3</Radio.Button>
            </Radio.Group>
          )}
        </Form.Item>

        <Form.Item {...formItemLayout} label="评分">
          {getFieldDecorator('rate', {
            initialValue: 3.5
          })(<Rate />)}
        </Form.Item>

        <Form.Item {...formItemLayout} label="上传" extra="可以上传一些文件">
          {getFieldDecorator('upload', {
            valuePropName: 'fileList',
            getValueFromEvent: this.normFile
          })(
            <Upload name="logo" action="/upload.do" listType="picture">
              <Button>
                <Icon type="upload" /> 点击上传
              </Button>
            </Upload>
          )}
        </Form.Item>

        <Form.Item {...formItemLayout} label="拖拽">
          <div className="dropbox">
            {getFieldDecorator('dragger', {
              valuePropName: 'fileList',
              getValueFromEvent: this.normFile
            })(
              <Upload.Dragger name="files" action="/upload.do">
                <p className="ant-upload-drag-icon">
                  <Icon type="inbox" />
                </p>
                <p className="ant-upload-text">点击或拖动文件到这个区域上传</p>
                <p className="ant-upload-hint">支持单个或批量上传。</p>
              </Upload.Dragger>
            )}
          </div>
        </Form.Item>

        <Form.Item wrapperCol={{ span: 12, offset: 6 }}>
          <Button type="primary" htmlType="submit">
            提交
          </Button>
        </Form.Item>
      </Form>
    );
  }
}

const Page = Form.create({})(Page1);
export default Page;
